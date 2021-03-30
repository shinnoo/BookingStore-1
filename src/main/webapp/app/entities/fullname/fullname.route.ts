import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFullname, Fullname } from 'app/shared/model/fullname.model';
import { FullnameService } from './fullname.service';
import { FullnameComponent } from './fullname.component';
import { FullnameDetailComponent } from './fullname-detail.component';
import { FullnameUpdateComponent } from './fullname-update.component';

@Injectable({ providedIn: 'root' })
export class FullnameResolve implements Resolve<IFullname> {
  constructor(private service: FullnameService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFullname> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((fullname: HttpResponse<Fullname>) => {
          if (fullname.body) {
            return of(fullname.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Fullname());
  }
}

export const fullnameRoute: Routes = [
  {
    path: '',
    component: FullnameComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Fullnames',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FullnameDetailComponent,
    resolve: {
      fullname: FullnameResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Fullnames',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FullnameUpdateComponent,
    resolve: {
      fullname: FullnameResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Fullnames',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FullnameUpdateComponent,
    resolve: {
      fullname: FullnameResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Fullnames',
    },
    canActivate: [UserRouteAccessService],
  },
];
