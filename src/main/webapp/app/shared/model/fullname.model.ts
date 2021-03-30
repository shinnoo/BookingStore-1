export interface IFullname {
  id?: number;
  firstName?: string;
  lastName?: string;
}

export class Fullname implements IFullname {
  constructor(public id?: number, public firstName?: string, public lastName?: string) {}
}
