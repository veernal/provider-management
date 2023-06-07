import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetProviderComponent } from './get-provider.component';

describe('GetProviderComponent', () => {
  let component: GetProviderComponent;
  let fixture: ComponentFixture<GetProviderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetProviderComponent]
    });
    fixture = TestBed.createComponent(GetProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
