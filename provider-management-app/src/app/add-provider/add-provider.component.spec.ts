import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProviderComponent } from './add-provider.component';

describe('AddProviderComponent', () => {
  let component: AddProviderComponent;
  let fixture: ComponentFixture<AddProviderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddProviderComponent]
    });
    fixture = TestBed.createComponent(AddProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
