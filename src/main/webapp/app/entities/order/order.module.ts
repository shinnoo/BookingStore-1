import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookStoreSharedModule } from 'app/shared/shared.module';
import { OrderComponent } from './order.component';
import { OrderDetailComponent } from './order-detail.component';
import { OrderUpdateComponent } from './order-update.component';
import { OrderDeleteDialogComponent } from './order-delete-dialog.component';
import { orderRoute } from './order.route';

@NgModule({
  imports: [BookStoreSharedModule, RouterModule.forChild(orderRoute)],
  declarations: [OrderComponent, OrderDetailComponent, OrderUpdateComponent, OrderDeleteDialogComponent],
  entryComponents: [OrderDeleteDialogComponent],
})
export class BookStoreOrderModule {}
