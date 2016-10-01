package mapas.guerrero.zambrano.com.mapas;

import com.orm.SugarRecord;

/**
 * Created by User on 28/12/2015.
 */
public class Todo  extends SugarRecord {
    private String todo;

    public Todo() {
    }

    public Todo(String todo) {
        this.todo = todo;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }
}
