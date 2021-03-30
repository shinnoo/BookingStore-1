export interface ICustomer {
  id?: number;
  age?: number;
  addressId?: number;
  fullnameId?: number;
}

export class Customer implements ICustomer {
  constructor(public id?: number, public age?: number, public addressId?: number, public fullnameId?: number) {}
}
