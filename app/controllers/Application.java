package controllers;

import models.*;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	static Form<Task> submitForm = new Form<Task>(Task.class);

	public static Result index() {
		return ok(index.render("Welcome to TODO list.", submitForm, Task.all() ) );
	}

	public static Result createTask() {
		//Task.create(submitForm.bindFromRequest().get());
		Form<Task> bindForm = submitForm.bindFromRequest();
		//submitForm.discardErrors();
		if(bindForm.hasErrors() == false) {
			Task.create(bindForm.get());
			submitForm = new Form<Task>(Task.class);
			return redirect("/");
		}
		return ok(index.render("Welcome to TODO list.", submitForm, Task.all() ) );
	}

	
	
	public static Result deleteTask(int id) {
		Task.remove(id);
		return redirect("/");
	}
	

}
