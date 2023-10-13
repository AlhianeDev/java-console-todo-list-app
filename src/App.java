import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.LinkedList;

public class App {

    static BufferedReader bufferedReader = new BufferedReader(

            new InputStreamReader(System.in)

    );

    static LinkedList<Todo> linkedList = new LinkedList<Todo>();

    public static void main(String[] args) throws Exception {

        System.out.println("\nTodo List App");

        Operations operation = null;

        do {

            try {

                operation = chose_operation();

                switch (operation) {

                    case CREATE:

                        create_new_todo();

                        break;

                    case UPDATE:

                        update_todo_by_id();

                        break;

                    case READ:

                        show_todos();

                        break;

                    case DELETE:

                        delete_todo_by_id();

                        break;

                    case SEARCH:

                        search_for_todo_by_title();

                        break;

                    case EXIT:

                        System.out.println("\nExit Program...");

                        break;

                    default:

                        System.out.println("\nUnknown Operation!");

                        break;

                }

            } catch (NumberFormatException e) {

                System.err.println("\nId Should Be A Number Not String!");

            } catch (IllegalArgumentException e) {

                System.err.println("\nUnknown Operation!");

            }

        } while (!operation.equals(Operations.EXIT));

    }

    private static Operations chose_operation() throws IOException {

        System.out.println("\nChoose One Of These Operations:");

        System.out.println("\n- CREATE => Create A New Todo.");

        System.out.println("\n- UPDATE => Update Todo By Id.");

        System.out.println("\n- READ => Display All Todos.");

        System.out.println("\n- DELETE => Delete Todo By Id.");

        System.out.println("\n- SEARCH => Search For Todo By Title.");

        System.out.println("\n- EXIT => Exit The Program.");

        System.out.print("\nOperation Name: ");

        return Operations.valueOf(bufferedReader.readLine());

    }

    private static void create_new_todo() throws IOException {

        System.out.print("\nTodo Id: ");

        int todo_id = Integer.parseInt(bufferedReader.readLine());

        System.out.print("\nTodo Title: ");

        String todo_title = bufferedReader.readLine();

        Todo todo = new Todo(todo_id, todo_title);

        linkedList.add(todo);

        System.out.println("\nTodo Added Successfuly!");

    }

    private static void update_todo_by_id() throws IOException {

        System.out.print("\nTodo Id: ");

        int todo_id = Integer.parseInt(bufferedReader.readLine());

        System.out.print("\nNew Todo Title: ");

        String new_todo_title = bufferedReader.readLine();

        boolean flag = false;

        for (Todo todo : linkedList) {

            if (todo.getId() == todo_id) {

                todo.setTitle(new_todo_title);

                flag = true;

            }

        }

        if (!flag)

            System.out.println("\nSorry, But No Todo With Your Inputed Id!");

        else

            System.out.println("\nTodo With Id " + todo_id + " Updated Successfuy!");

    }

    private static void show_todos() {

        if (linkedList.size() > 0)

            for (Todo todo : linkedList)

                System.out.println("\n# " + todo.getId() + " - " + todo.getTitle());

        else

            System.out.println("\nSorry, But No Todos To Show!");

    }

    private static void delete_todo_by_id() throws IOException {

        System.out.print("\nTodo Id: ");

        int todo_id = Integer.parseInt(bufferedReader.readLine());

        int index = -1, counter = 0;

        for (Todo todo : linkedList) {

            if (todo.getId() == todo_id)

                index = counter;

            counter++;

        }

        if (index != -1) {

            linkedList.remove(index);

            System.out.println("\nTodo With Id " + todo_id + " Deleted Successfuy!");

        } else {

            System.out.println("\nSorry, But No Todo With Your Inputed Id!");

        }

    }

    private static void search_for_todo_by_title() throws IOException {

        System.out.print("\nSearch Term - Todo Title: ");

        String todo_title = bufferedReader.readLine();

        Todo targeted_todo = null;

        for (Todo todo : linkedList) {

            if (todo.getTitle() == todo_title)

                targeted_todo = todo;

        }

        if (todo_title != null) {

            int index = linkedList.indexOf(targeted_todo);

            System.out.println(

                    "\nTodo With Title " + todo_title + " Found At Index: " + index

            );

        } else {

            System.out.println("\nSorry, But No Todo With Your Inputed Title!");

        }

    }

}
