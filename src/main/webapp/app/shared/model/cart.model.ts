export interface ICart {
  id?: number;
  quantity?: number;
  paymentId?: number;
}

export class Cart implements ICart {
  constructor(public id?: number, public quantity?: number, public paymentId?: number) {}
}
