using UnityEngine;
using System.Collections;

[RequireComponent(typeof(PlayerPhysics))]
public class PlayerController : MonoBehaviour
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

	void Start ()
	{
		playerPhysics = GetComponent<PlayerPhysics> ();
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
			if (playerPhysics.grounded) {
				amountToMove.y = 0;
				if (touch.tapCount == 2) {
					amountToMove.y = jumpHeight;
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
