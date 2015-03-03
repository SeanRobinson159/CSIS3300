using UnityEngine;
using System.Collections;

public class Death : MonoBehaviour {
	void OnTriggerEnter(Collider c){
		if (c.tag == "Player") {
			c.GetComponent<Entity>().TakeDamage(1000);
		}
	}
}
