import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Order } from './models';
import { OrderService } from './service/order.service';
import { OrderComponent } from './component/order.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit, AfterViewInit {

  @ViewChild(OrderComponent) //To use child's method
  child!: OrderComponent;

  constructor(private orderSvc: OrderService) { }
  
  ngOnInit(): void { }
	ngAfterViewInit(): void { }
  
  addOrder(order: Order) {

    console.info('>>>New order: ', order);

		this.orderSvc.saveOrder(order)
			.then(result => {
				alert(`Order id ${result.orderId} is placed.`);
				this.child.clearData();
			})
			.catch(error => {
				alert(`ERROR! ${JSON.stringify(error)}`)
			})

  }

}
