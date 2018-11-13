import { Injectable } from '@angular/core';
import { Todo } from './todo';
//import { Observable } from "rxjs/Observable";
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
//import 'rxjs/add/operator/toPromise';

@Injectable()
export class TodoService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http:HttpClient) { }

  getTodos():  Promise<Todo[]> {
    return this.http.get(this.baseUrl + '/api/todos/')
      .toPromise()
      .then(response => response as Todo[])
      .catch(this.handleError);
  }

  createTodo(todoData: Todo): Promise<Todo> {
    return this.http.post(this.baseUrl + '/api/todos/', todoData)
      .toPromise().then(response => response as Todo)
      .catch(this.handleError);
  }

  updateTodo(todoData: Todo): Promise<Todo> {
    return this.http.put(this.baseUrl + '/api/todos/' + todoData.title, todoData.id)
      .toPromise()
      .then(response => response as Todo)
      .catch(this.handleError);
  }

  getTodo(title: string):  Promise<Todo[]> {
    return this.http.get(this.baseUrl + '/api/todos/' + title)
      .toPromise()
      .then(response => response as Todo[])
      .catch(this.handleError);
  }

  deleteTodo(title: string): Promise<any> {
    return this.http.delete(this.baseUrl + '/api/todos/' + title)
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    return Promise.reject(error.message || error);
  }
}