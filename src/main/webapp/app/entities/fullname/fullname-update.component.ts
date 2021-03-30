import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFullname, Fullname } from 'app/shared/model/fullname.model';
import { FullnameService } from './fullname.service';

@Component({
  selector: 'jhi-fullname-update',
  templateUrl: './fullname-update.component.html',
})
export class FullnameUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    firstName: [],
    lastName: [],
  });

  constructor(protected fullnameService: FullnameService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fullname }) => {
      this.updateForm(fullname);
    });
  }

  updateForm(fullname: IFullname): void {
    this.editForm.patchValue({
      id: fullname.id,
      firstName: fullname.firstName,
      lastName: fullname.lastName,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fullname = this.createFromForm();
    if (fullname.id !== undefined) {
      this.subscribeToSaveResponse(this.fullnameService.update(fullname));
    } else {
      this.subscribeToSaveResponse(this.fullnameService.create(fullname));
    }
  }

  private createFromForm(): IFullname {
    return {
      ...new Fullname(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFullname>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
