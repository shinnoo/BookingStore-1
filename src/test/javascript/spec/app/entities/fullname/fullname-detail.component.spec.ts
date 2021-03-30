import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BookStoreTestModule } from '../../../test.module';
import { FullnameDetailComponent } from 'app/entities/fullname/fullname-detail.component';
import { Fullname } from 'app/shared/model/fullname.model';

describe('Component Tests', () => {
  describe('Fullname Management Detail Component', () => {
    let comp: FullnameDetailComponent;
    let fixture: ComponentFixture<FullnameDetailComponent>;
    const route = ({ data: of({ fullname: new Fullname(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BookStoreTestModule],
        declarations: [FullnameDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FullnameDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FullnameDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load fullname on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fullname).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
