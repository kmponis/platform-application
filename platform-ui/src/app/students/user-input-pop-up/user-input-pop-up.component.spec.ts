import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserInputPopUpComponent } from './user-input-pop-up.component';

describe('UserInputPopUpComponent', () => {
  let component: UserInputPopUpComponent;
  let fixture: ComponentFixture<UserInputPopUpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserInputPopUpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserInputPopUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
