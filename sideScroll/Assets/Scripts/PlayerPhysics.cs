﻿using UnityEngine;
using System.Collections;


[RequireComponent (typeof(BoxCollider))]
public class PlayerPhysics : MonoBehaviour {

	public LayerMask collisionMask;

	private BoxCollider collider;
	private Vector3 s;
	private Vector3 c;

	private Vector3 originalSize;
	private Vector3 originalCenter;
	private float colliderScale;

	private int collisionDivisionsX = 4;
	private int collisionDivisionsY = 4;


	private float skin = .005f;
	
	[HideInInspector]
	public bool grounded;

	[HideInInspector]
	public bool movementStopped;

	//Moving platform
	private Transform platform;
	private Vector3 platformPositionOld;
	private Vector3 deltaPlatformPos;
	//End moving Platform

	Ray ray;
	RaycastHit hit;

	void Start() {
		collider = GetComponent<BoxCollider>();
		colliderScale = transform.localScale.x;

		originalSize = collider.size;
		originalCenter = collider.center;
		SetCollider (originalSize, originalCenter);
	}
	
	public void Move(Vector2 moveAmount) {
		
		float deltaY = moveAmount.y;
		float deltaX = moveAmount.x;
		Vector2 p = transform.position;

		if (platform) {
			deltaPlatformPos = platform.position - platformPositionOld;
		} else {
			deltaPlatformPos = Vector3.zero;
		}

		#region Vertical Collisions
		// Check collisions above and below
		grounded = false;

		for (int i = 0; i<collisionDivisionsX; i ++) {
			float dir = Mathf.Sign(deltaY);
			float x = (p.x + c.x - s.x/2) + s.x/(collisionDivisionsX-1) * i; // Left, centre and then rightmost point of collider
			float y = p.y + c.y + s.y/2 * dir; // Bottom of collider
			
			ray = new Ray(new Vector2(x,y), new Vector2(0,dir));
			//Debug.DrawRay(ray.origin,ray.direction);
			if (Physics.Raycast(ray,out hit,Mathf.Abs(deltaY) + skin,collisionMask)) {

				platform = hit.transform;
				platformPositionOld = platform.position;

				// Get Distance between player and ground
				float dst = Vector3.Distance (ray.origin, hit.point);
				
				// Stop player's downwards movement after coming within skin width of a collider
				if (dst > skin) {
					deltaY = dst * dir - skin * dir;
				} else {
					deltaY = 0;
				}
				grounded = true;
				break;
				
			}
			else {
				platform = null;
			}
		}
		#endregion

		#region Sidways Collisions
		// Check collisions left and right
		movementStopped = false;
		for (int i = 0; i<collisionDivisionsY; i ++) {
			float dir = Mathf.Sign(deltaX);

			float x = p.x + c.x + s.x/2 * dir;
			float y = p.y + c.y - s.y/2 + s.y/(collisionDivisionsY-1) * i;
			
			ray = new Ray(new Vector2(x,y), new Vector2(dir,0));
			//Debug.DrawRay(ray.origin,ray.direction);

			if (Physics.Raycast(ray,out hit,Mathf.Abs(deltaX) + skin,collisionMask)) {
				// Get Distance between player and ground
				float dst = Vector3.Distance (ray.origin, hit.point);
				
				// Stop player's downwards movement after coming within skin width of a collider
				if (dst > skin) {
					deltaX = dst * dir - skin * dir;
				} else {
					deltaX = 0;
				}
				movementStopped = true;
				break;
				
			}
		}
		#endregion

		if (!grounded && !movementStopped) {
			Vector3 playerDir = new Vector3 (deltaX, deltaY);
			Vector3 o = new Vector3 (p.x + c.x + s.x / 2 * Mathf.Sign (deltaX), p.y + c.y + s.y / 2 * Mathf.Sign (deltaY));
			ray = new Ray (o, playerDir.normalized);
			//Debug.DrawRay(o, playerDir.normalized);
			if (Physics.Raycast (ray, Mathf.Sqrt (deltaX * deltaX + deltaY + deltaY), collisionMask)) {
				grounded = true;
				deltaY = 0;
			}
		}

		Vector2 finalTransform = new Vector2(deltaX + deltaPlatformPos.x, deltaY);

		transform.Translate(finalTransform,Space.World);

	}

	public void SetCollider(Vector3 size, Vector3 center){
		collider.size = size;
		collider.center = center;

		s = size * colliderScale;
		c = center * colliderScale;
	}

}








