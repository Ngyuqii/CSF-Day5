import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {firstValueFrom} from "rxjs";
import { Order, OrderPlaced } from '../models';

const SB_URL = "http://localhost:8080/order"

@Injectable()
export class OrderService {

  constructor(private http: HttpClient) { }

  //Method to make a HTTP POST request to the server
  //Returns a Promise of OrderPlaced object and print console.info statement if promise is resolved, 
  saveOrder(order: Order): Promise<OrderPlaced> {

		return firstValueFrom(
			this.http.post<OrderPlaced>(SB_URL, order)
		).then(req => {
      console.info(`${order} posted to server.`);
      return req;
    });
  }

}
