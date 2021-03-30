export interface IOrder {
  id?: number;
  totalPrice?: number;
  customerId?: string;
  cartId?: number;
}

export class Order implements IOrder {
  constructor(public id?: number, public totalPrice?: number, public customerId?: string, public cartId?: number) {}
}
