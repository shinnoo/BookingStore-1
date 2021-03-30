import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookStoreSharedModule } from 'app/shared/shared.module';
import { FullnameComponent } from './fullname.component';
import { FullnameDetailComponent } from './fullname-detail.component';
import { FullnameUpdateComponent } from './fullname-update.component';
import { FullnameDeleteDialogComponent } from './fullname-delete-dialog.component';
import { fullnameRoute } from './fullname.route';

@NgModule({
  imports: [BookStoreSharedModule, RouterModule.forChild(fullnameRoute)],
  declarations: [FullnameComponent, FullnameDetailComponent, FullnameUpdateComponent, FullnameDeleteDialogComponent],
  entryComponents: [FullnameDeleteDialogComponent],
})
export class BookStoreFullnameModule {}
