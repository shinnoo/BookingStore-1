import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFullname } from 'app/shared/model/fullname.model';
import { FullnameService } from './fullname.service';

@Component({
  templateUrl: './fullname-delete-dialog.component.html',
})
export class FullnameDeleteDialogComponent {
  fullname?: IFullname;

  constructor(protected fullnameService: FullnameService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fullnameService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fullnameListModification');
      this.activeModal.close();
    });
  }
}
