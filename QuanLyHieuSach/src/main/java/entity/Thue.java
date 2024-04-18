
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author FPTSHOP
 */

@Entity
@Table(name = "Thue")
public class Thue {
	@Id
	@Column(name = "ID")
    private String id;
    private float thue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Thue(String id, float thue) {
        this.id = id;
        this.thue = thue;
    }

   
    public Thue() {
    }

  

    public float getThue() {
        return thue;
    }

    public void setThue(float thue) {
        this.thue = thue;
    }
    
    
}
