import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'customer',
        loadChildren: () => import('./customer/customer.module').then(m => m.BookStoreCustomerModule),
      },
      {
        path: 'fullname',
        loadChildren: () => import('./fullname/fullname.module').then(m => m.BookStoreFullnameModule),
      },
      {
        path: 'address',
        loadChildren: () => import('./address/address.module').then(m => m.BookStoreAddressModule),
      },
      {
        path: 'order',
        loadChildren: () => import('./order/order.module').then(m => m.BookStoreOrderModule),
      },
      {
        path: 'cart',
        loadChildren: () => import('./cart/cart.module').then(m => m.BookStoreCartModule),
      },
      {
        path: 'payment',
        loadChildren: () => import('./payment/payment.module').then(m => m.BookStorePaymentModule),
      },
      {
        path: 'item',
        loadChildren: () => import('./item/item.module').then(m => m.BookStoreItemModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class BookStoreEntityModule {}
