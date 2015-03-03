using UnityEngine;
using System.Collections;

public class Platform : MonoBehaviour {

	public float speed = 2;
	public float pDist = 10;
	public float origPositionX = -1000;

	void Update(){
		if (origPositionX == -1000) {
			origPositionX = transform.position.x;
		}
		if (transform.position.x > pDist) {
			speed = -speed;
		} 
		if (transform.position.x < origPositionX) {
			speed = -speed;
		}
		else {
		}
		transform.Translate (Vector3.right * speed * Time.deltaTime);

	}
}
