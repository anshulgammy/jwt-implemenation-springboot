import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CallResourcesComponent } from './call-resources.component';

describe('CallResourcesComponent', () => {
  let component: CallResourcesComponent;
  let fixture: ComponentFixture<CallResourcesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CallResourcesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CallResourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
