package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

/**
 * Manage a database of computers
 */
public class HomeController  extends Controller {

    private FormFactory formFactory;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }
    /**
     * This result directly redirect to application home.
     */
    public Result GO_HOME = Results.redirect(
        routes.HomeController.list(0, "name", "asc", "")
    );
    /**
     * Handle default path requests, redirect to computers list
     */
    public Result index() {
        return GO_HOME;
    }

    /**
     * Display the paginated list of computers.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on computer names
     */
    public Result list(int page, String sortBy, String order, String filter) {
        return ok(
            views.html.list.render(
                Computer.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Computer.
     *
     * @param id Id of the computer to edit
     */
    public Result edit(Long id) {
        Form<Computer> computerForm = formFactory.form(Computer.class).fill(
            Computer.find.byId(id)
        );
        return ok(
            views.html.editForm.render(id, computerForm)
        );
    }
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the computer to edit
     */
    public Result update(Long id) throws PersistenceException {
        Form<Computer> computerForm = formFactory.form(Computer.class).bindFromRequest();
        if(computerForm.hasErrors()) {
            return badRequest(views.html.editForm.render(id, computerForm));
        }
        Transaction txn = Ebean.beginTransaction();
        try {
            Computer savedComputer = Computer.find.byId(id);
            if (savedComputer != null) {
                Computer newComputerData = computerForm.get();
                savedComputer.company = newComputerData.company;
                savedComputer.discontinued = newComputerData.discontinued;
                savedComputer.introduced = newComputerData.introduced;
                savedComputer.name = newComputerData.name;

                savedComputer.update();
                flash("success", "Computer " + computerForm.get().name + " has been updated");
                txn.commit();
            }
        } finally {
            txn.end();
        }

        return GO_HOME;
    }
    
    /**
     * Display the 'new computer form'.
     */
    public Result create() {
        Form<Computer> computerForm = formFactory.form(Computer.class);
        return ok(
                views.html.createForm.render(computerForm)
        );
    }
    
    /**
     * Handle the 'new computer form' submission 
     */
    private static long cont=574;
    public Result save() {
    	
    	Computer c1= new Computer(); //objeto k guarla los datos de la interfaz
        Form<Computer> computerForm = formFactory.form(Computer.class).bindFromRequest();
        if(computerForm.hasErrors()) {
            return badRequest(views.html.createForm.render(computerForm));
        }
        
        c1=computerForm.get();
        c1.id=(long) ++cont;
        c1.save();
        
        //computerForm.get().save();
        flash("success", "Computer " + computerForm.get().name + " has been created");
        return GO_HOME;
    }
    
    /**
     * Handle computer deletion
     */
    public Result delete(Long id) {
        Computer.find.ref(id).delete();
        flash("success", "Computer has been deleted");
        return GO_HOME;
    }
    
    //MEMORIA RAM
   public Result list1(int page, String sortBy, String order, String filter) {
        return ok(
            views.html.list1.render(
                Mram.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Computer.
     *
     * @param id Id of the computer to edit
     */
     public Result edit1(Long id) {
        Form<Mram> mramForm = formFactory.form(Mram.class).fill(
            Mram.find.byId(id)
        );
        return ok(
            views.html.editForm1.render(id, mramForm)
        );
    } 
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the computer to edit
     */
    public Result update1(Long id) throws PersistenceException {
        Form<Mram> mramForm = formFactory.form(Mram.class).bindFromRequest();
        if(mramForm.hasErrors()) {
            return badRequest(views.html.editForm1.render(id, mramForm));
        }
        Transaction txn = Ebean.beginTransaction();
        try {
            Mram savedMram = Mram.find.byId(id);
            if (savedMram != null) {
                Mram newComputerData = mramForm.get();
                savedMram.company = newComputerData.company;
                savedMram.pines = newComputerData.pines;
                savedMram.capacidadm = newComputerData.capacidadm;
                savedMram.tipo = newComputerData.tipo;

                savedMram.update();
                flash("success", "Mram " + mramForm.get().tipo + " has been updated");
                txn.commit();
            }
        } finally {
            txn.end();
        }

        return GO_HOME;
    }
    
    /**
     * Display the 'new computer form'.
     */
    public Result create1() {
        Form<Mram> mramForm = formFactory.form(Mram.class);
        return ok(
                views.html.createRam.render(mramForm)
        );
    }
    
    /**
     * Handle the 'new computer form' submission 
     */
    private static long cont1=574;
    public Result save1() {
    	
    	Mram c1= new Mram(); //objeto k guarla los datos de la interfaz
        Form<Mram> mramForm = formFactory.form(Mram.class).bindFromRequest();
        if(mramForm.hasErrors()) {
            return badRequest(views.html.createRam.render(mramForm));
        }
        
        c1=mramForm.get();
        c1.id=(long) ++cont1;
        c1.save();
        
        //computerForm.get().save();
        flash("success", "Mram " + mramForm.get().tipo + " has been created");
        return GO_HOME;
    }
    
    /**
     * Handle computer deletion
     */
    public Result delete1(Long id) {
        Mram.find.ref(id).delete();
        flash("success", "Mram has been deleted");
        return GO_HOME;
    }
    
    
}
            
