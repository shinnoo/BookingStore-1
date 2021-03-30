import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFullname } from 'app/shared/model/fullname.model';

type EntityResponseType = HttpResponse<IFullname>;
type EntityArrayResponseType = HttpResponse<IFullname[]>;

@Injectable({ providedIn: 'root' })
export class FullnameService {
  public resourceUrl = SERVER_API_URL + 'api/fullnames';

  constructor(protected http: HttpClient) {}

  create(fullname: IFullname): Observable<EntityResponseType> {
    return this.http.post<IFullname>(this.resourceUrl, fullname, { observe: 'response' });
  }

  update(fullname: IFullname): Observable<EntityResponseType> {
    return this.http.put<IFullname>(this.resourceUrl, fullname, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFullname>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFullname[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
