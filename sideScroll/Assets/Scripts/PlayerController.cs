using UnityEngine;
using System.Collections;

[RequireComponent(typeof(PlayerPhysics))]
public class PlayerController : MonoBehaviour {

	//Player Handeling
	public float gravity = 20;
	public float speed = 8;
	public float acceleration = 30;
	public float jumpHeight = 12;

	private float currentSpeed;
	private float targetSpeed;
	private Vector2 amountToMove;
	private Touch touch;

	private PlayerPhysics playerPhysics;


	void Start () {
		playerPhysics = GetComponent<PlayerPhysics> ();
	}
	
	void Update () {
		if (Application.platform == RuntimePlatform.Android) {
				androidTouchInput ();
		} else {
			if (playerPhysics.movementStopped) {
				targetSpeed = 0;
				currentSpeed = 0;
			}

			targetSpeed = Input.GetAxisRaw ("Horizontal") * speed;
			currentSpeed = IncrementTowards (currentSpeed, targetSpeed, acceleration);

			if (playerPhysics.grounded) {
				amountToMove.y = 0;
				if (Input.GetButtonDown ("Jump")) {
					amountToMove.y = jumpHeight;
				}

			}

			amountToMove.x = currentSpeed;
			amountToMove.y -= gravity * Time.deltaTime;
			playerPhysics.Move (amountToMove * Time.deltaTime); 

		}
	}

	private void androidTouchInput(){
		if (playerPhysics.movementStopped) {
			targetSpeed = 0;
			currentSpeed = 0;
		}

		//Touch input
//		if (Input.touchCount == 0) {
//			return;
//		}
		touch = Input.touches[0];
		if (touch.position.x > Screen.width / 2) {
//			if(touch.phase == TouchPhase.Began)
//			targetSpeed = Input.GetAxisRaw ("Horizontal") * speed;	// TODO get this fixed!
			print("Right side");
		} else if (touch.position.x < Screen.width / 2) {
//			if(touch.phase == TouchPhase.Began)
//			targetSpeed = Input.GetAxisRaw ("Horizontal") * speed;	// AND THIS
			print("Left side");
		} 
		//End touch input
		currentSpeed = IncrementTowards (currentSpeed, targetSpeed, acceleration);
		
		if (playerPhysics.grounded) {
			amountToMove.y = 0;
			touch = Input.GetTouch(0);
			if(touch.tapCount == 2){
				amountToMove.y = jumpHeight;
			}
		}
		
		amountToMove.x = currentSpeed;
		amountToMove.y -= gravity * Time.deltaTime;
		playerPhysics.Move (amountToMove * Time.deltaTime);
	}

	private float IncrementTowards(float n, float target, float a){
		if (n == target) {
			return n;
		} else {
			float dir = Mathf.Sign (target - n);		// must n be increased or decreased to get closer to target
			n += a * Time.deltaTime * dir;
			return (dir == Mathf.Sign (target - n)) ? n : target;	// if n has now passed target then return target, otherwise return n
		}
	}


}
