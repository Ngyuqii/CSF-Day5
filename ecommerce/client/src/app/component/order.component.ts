import { Component, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject, debounceTime} from 'rxjs';
import { Order } from '../models';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})

export class OrderComponent implements OnInit {
  
  constructor(private fb: FormBuilder){ }
	
  form!: FormGroup;
  itemsArray!: FormArray;

  obs!: Observable<any>;

  @Output()
	onNewOrder = new Subject<Order>()

  //Initialize form creation
  //Observable emits event on every value change of the form with a delay of 500ms
  ngOnInit(): void {
    this.form = this.createForm();
    this.obs = this.form.valueChanges.pipe(debounceTime(500));
  }

  //Method to create a form with form controls and validators
  //Include the itemsarray
  private createForm(): FormGroup {
    //Create an array form control that is empty - requires a minimum of 1 element
    this.itemsArray = this.fb.array([], [Validators.minLength(1)]);

    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required ]),
			email: this.fb.control<string>('', [ Validators.required, Validators.email ]),
			lineItems: this.itemsArray
    })
  }

  //Method to create a sub-form when clicked on addItem
  //Add this sub-form into the linesArray
  addItem() {
    const i = this.fb.group({
      item: this.fb.control<string>('', [ Validators.required ]),
			quantity: this.fb.control<number>(1,[ Validators.required, Validators.min(1) ])
    })
    this.itemsArray.push(i);
    console.log('>>>Added new line.');
  }

  //Method to remove form from the program array at index parameter
  deleteItem(i: number) {
    this.itemsArray.removeAt(i);
    console.log('>>>Removed line item:', i);
  }

  //Method to refresh the form
  clearData() {
		this.form = this.createForm();
	}

  //Method that returns true if specific form input is invalid
  invalidControl(ctrlName: string): boolean {
    const ctrl = this.form.get(ctrlName) as FormControl;
    return ctrl.invalid && (!ctrl.pristine);
  }

  //Method that returns true if form is invalid or has no line item
  invalidForm(): boolean {
    return this.form.invalid || this.itemsArray.controls.length <= 0;
  }

  //Method upon ngSubmit to retrieve values of the form controls and set into Order object
  //Emits the newOrder object as an event
  //Resets form
  placeOrder() {
    const newOrder = this.form.value as Order;
    this.onNewOrder.next(newOrder);
    this.clearData();
  }

}