package models;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

/**
 * Microprocesador entity managed by Ebean
 */
@Entity 
public class Microprocesador extends Model {

    private static final long serialVersionUID1 = 1L;

	@Id
   public Long id;
    
    @Constraints.Required
    public Integer velocidad;
    
    @Constraints.Required
    public Integer capacidad;
    
    @ManyToOne
    public Computer computer;
    
    /**
     * Generic query helper for entity Microprocesador with id Long
     */
    public static Find<Long,Microprocesador> find = new Find<Long,Microprocesador>(){};
    
    /**
     * Return a paged list of microprocesador
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static PagedList<Microprocesador> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
            find.where()
                .ilike("velocidad", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("computer")
                .findPagedList(page, pageSize);
    }
    
}

