package unit

import javax.persistence.*
import java.io.Serializable

/**
 * Created by bebe on 12/20/14.
 */
Entity
Table(name = "User")
public class City(): Serializable {

    public var cityId: Int = 0
        [Id
        GeneratedValue(strategy = GenerationType.IDENTITY)
        Column(name = "cityId", unique = true, nullable = false)]
        get

    public var city: String = ""
        [Column(name = "city", unique = false, nullable = false, length = 40)]
        get

    public var countryId: Int = 18
        [Column(name = "country_id", unique = false, nullable = false)]
        get

    override public fun toString(): String {
        return "User [id=$cityId, city=$city, countryId=$countryId]";
    }

}

