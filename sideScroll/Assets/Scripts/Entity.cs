using UnityEngine;
using System.Collections;

public class Entity : MonoBehaviour {
	public float health;
	public bool isDead;

	public void TakeDamage(float dmg){
		health -= dmg;

		if (health <= 0) {
			Die();
		}
	}

	public void Die(){
		Destroy (this.gameObject);
		Application.LoadLevel (Application.loadedLevel);
		isDead = true;
	}
}
