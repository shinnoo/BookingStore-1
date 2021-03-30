import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BookStoreTestModule } from '../../../test.module';
import { FullnameUpdateComponent } from 'app/entities/fullname/fullname-update.component';
import { FullnameService } from 'app/entities/fullname/fullname.service';
import { Fullname } from 'app/shared/model/fullname.model';

describe('Component Tests', () => {
  describe('Fullname Management Update Component', () => {
    let comp: FullnameUpdateComponent;
    let fixture: ComponentFixture<FullnameUpdateComponent>;
    let service: FullnameService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BookStoreTestModule],
        declarations: [FullnameUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FullnameUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FullnameUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FullnameService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Fullname(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Fullname();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
