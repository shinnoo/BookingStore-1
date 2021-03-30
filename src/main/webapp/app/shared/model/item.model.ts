export interface IItem {
  id?: number;
  name?: string;
  price?: number;
  cartId?: number;
}

export class Item implements IItem {
  constructor(public id?: number, public name?: string, public price?: number, public cartId?: number) {}
}
