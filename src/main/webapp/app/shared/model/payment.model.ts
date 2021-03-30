import { Moment } from 'moment';

export interface IPayment {
  id?: number;
  createAt?: Moment;
  code?: string;
}

export class Payment implements IPayment {
  constructor(public id?: number, public createAt?: Moment, public code?: string) {}
}
