export interface IAddress {
  id?: number;
  street?: string;
  city?: string;
}

export class Address implements IAddress {
  constructor(public id?: number, public street?: string, public city?: string) {}
}
