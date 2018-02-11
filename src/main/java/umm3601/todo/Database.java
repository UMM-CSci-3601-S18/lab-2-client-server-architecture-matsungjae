package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class Database {
  private Todo[] allTodos;

  public Database(String userDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(userDataFile);
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  public Todo[] listTodos() {

    return allTodos;
  }

  public Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;

    // Filter category if defined
    if(queryParams.containsKey("category")) {
      String targetCategory = queryParams.get("category")[0];
      filteredTodos = filterTodosByCategory(filteredTodos, targetCategory);
    }
    // Filter by a string contained in the body
    if(queryParams.containsKey("body")) {
      String targetString = queryParams.get("body")[0];
      filteredTodos = filterTodosByBody(filteredTodos, targetString);
    }
    //Filter by owner
    if(queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner")[0];
      filteredTodos = filterTodosByOwner(filteredTodos, targetOwner);
    }

    //Filter by status (complete = true, incomplete = false)
    if(queryParams.containsKey("status")){
      String strStatus = queryParams.get("status")[0];
      Boolean status = null;
      if (strStatus.equals("complete")) {
        status = true;
      }
      else if (strStatus.equals("incomplete")) {
        status = false;
      }
      if (status != null) {
        filteredTodos = filterTodosByStatus(filteredTodos, status);
      }
    }

    //Sort alphabetically by a field
    if(queryParams.containsKey("orderBy")) {
      String field = queryParams.get("orderBy")[0];

      sortTodos(filteredTodos, field);
    }

    // limit the number of items displayed
    if(queryParams.containsKey("limit")) {
      int limit = Integer.parseInt(queryParams.get("limit")[0]);
      filteredTodos = limitTodos(filteredTodos, limit);
    }

    return filteredTodos;
  }

  public Todo[] filterTodosByCategory(Todo[] Todos, String targetCategory) {
    return Arrays.stream(Todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByBody(Todo[] todos, String targetString) {
    return Arrays.stream(todos).filter(x -> x.body.contains(targetString)).toArray(Todo[]::new);
  }

  public Todo[] limitTodos(Todo[] todos, int limit) {
    Todo[] filteredTodos = new Todo[limit];
    System.arraycopy(todos, 0, filteredTodos, 0, limit);
    return filteredTodos;
  }

  public Todo[] filterTodosByStatus(Todo[] todos, boolean status) {
    return Arrays.stream(todos).filter(x -> x.status == status).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByOwner(Todo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }

  /***
   * Sorts the array of todos by the field given in the string. If the field given in the string is not something that
   * can be sorted, then the array is returned unsorted.
   * @param todos array to be sorted
   * @param field which field to sort by
   * @return the sorted array
   */
  public Todo[] sortTodos(Todo[] todos, String field) {

    switch (field) {
      case "category" : {
        Arrays.sort(todos, new sortByCategory());
        return todos;
      }
      case "owner" : {
        Arrays.sort(todos,new sortByOwner());
        return todos;
      }
      case "body" : {
        Arrays.sort(todos, new sortByBody());
        return todos;
      }
      case "status" : {
        Arrays.sort(todos, new sortByStatus());
        return todos;
      }
    }
    return todos;
  }
}
