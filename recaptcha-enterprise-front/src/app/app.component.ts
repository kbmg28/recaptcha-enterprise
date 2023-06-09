import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ReCaptchaV3Service } from 'ng-recaptcha';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  loginForm = new FormGroup({
    login: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  constructor(private recaptchaV3Service: ReCaptchaV3Service) {

  }

  get login() {    return this.loginForm.get('login'); }
  get password() {    return this.loginForm.get('password'); }

  public send(): void {
    this.recaptchaV3Service.execute('login')
    .subscribe((token: string) => {
      console.log(`Token [${token}] generated`);
    });
  }
}
