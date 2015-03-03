using UnityEngine;
using System.Collections;

[RequireComponent(typeof(PlayerPhysics))]
public class PlayerController : Entity 
{
	public float gravity = 20;
	public float walkSpeed = 8;
	public float runSpeed = 12;
	public float acceleration = 30;
	public float jumpHeight = 12;
	private float animationSpeed;
	private float currentSpeed;
	private float targetSpeed;
	private Vector2 amountToMove;
	private Touch touch;
	private PlayerPhysics playerPhysics;
	private GameManager manager;

	//Swiping
	private float fingerStartTime = 0.0f;
	private Vector2 fingerStartPos = Vector2.zero;
	private bool isSwipe = false;
	private float minSwipeDist = 50.0f;
	private float maxSwipeTime = 0.5f;
	//End Swiping

	void Start ()
	{
		playerPhysics = GetComponent<PlayerPhysics> ();
		manager = Camera.main.GetComponent<GameManager> ();
	}
	
	void Update ()
	{
		if (playerPhysics.movementStopped) {
			targetSpeed = 0;
			currentSpeed = 0;
		}

		if (Application.platform == RuntimePlatform.Android) {
			androidTouchInput ();
		} else {
			keyboardInput ();
		}
		currentSpeed = IncrementTowards (currentSpeed, targetSpeed, acceleration);
		amountToMove.x = currentSpeed;
		amountToMove.y -= gravity * Time.deltaTime;
		playerPhysics.Move (amountToMove * Time.deltaTime);
		if (isDead) {

		}
	}

	private void keyboardInput ()
	{
		float speed = (Input.GetButton ("Run")) ? runSpeed : walkSpeed;
		targetSpeed = Input.GetAxisRaw ("Horizontal") * speed;
		if (playerPhysics.grounded) {
			amountToMove.y = 0;
			if (Input.GetButtonDown ("Jump")) {
				amountToMove.y = jumpHeight;
			}
		}
	}

	private void androidTouchInput ()
	{
		foreach (Touch touch in Input.touches) {
			float speed = (Input.GetButton ("Run")) ? runSpeed : walkSpeed;
			if (touch.phase == TouchPhase.Began) {
				if (touch.position.x > (2 * Screen.width / 3)) {	//Right Side
					targetSpeed = speed;
				} else if (touch.position.x < Screen.width / 3) {	//Left Side
					targetSpeed = -speed;
				} else if (touch.position.x > Screen.width / 3 && touch.position.x < (2 * Screen.width / 3)) {	//Middle
					targetSpeed = 0;
				}
				isSwipe = true;
				fingerStartTime = Time.time;
				fingerStartPos = touch.position;
			}
			if (touch.phase == TouchPhase.Stationary) {
			}
			if (touch.phase == TouchPhase.Moved) {
			}
			if (touch.phase == TouchPhase.Canceled) {
			}
			if (touch.phase == TouchPhase.Ended) {
				targetSpeed = 0;
			}
			checkSwipe (touch, speed);
		}
	}

	void OnTriggerEnter(Collider c){
		if (c.tag == "Checkpoint") {
			manager.setCheckpoint(c.transform.position);
		}
		if(c.tag == "Finish"){
			manager.EndLevel();
		}
	}

	private void checkSwipe (Touch touch, float speed)
	{
		float gestureTime = Time.time - fingerStartTime;
		float gestureDist = (touch.position - fingerStartPos).magnitude;
		
		if (isSwipe && gestureTime < maxSwipeTime && gestureDist > minSwipeDist) {
			Vector2 direction = touch.position - fingerStartPos;
			Vector2 swipeType = Vector2.zero;
			
			if (Mathf.Abs (direction.x) > Mathf.Abs (direction.y)) {
				// the swipe is horizontal:
				swipeType = Vector2.right * Mathf.Sign (direction.x);
			} else {
				// the swipe is vertical:
				swipeType = Vector2.up * Mathf.Sign (direction.y);
			}
			
			if (swipeType.x != 0.0f) {
				if (swipeType.x > 0.0f) {
					// MOVE RIGHT
				} else {
					// MOVE LEFT
				}
			}
			if (playerPhysics.grounded) {
				amountToMove.y = 0;
				if (swipeType.y != 0.0f) {
					if (swipeType.y > 0.0f) {
						amountToMove.y = jumpHeight;
						isSwipe = false;
					}
				} else {
					// MOVE DOWN
				}
			}
			
		}
	}

	private float IncrementTowards (float n, float target, float a)
	{
		if (n == target) {
			return n;
		} else {
			float dir = Mathf.Sign (target - n);		// must n be increased or decreased to get closer to target
			n += a * Time.deltaTime * dir;
			return (dir == Mathf.Sign (target - n)) ? n : target;	// if n has now passed target then return target, otherwise return n
		}
	}


}
