import { TestControllerService } from './open-api/api/testController.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ReCaptchaV3Service } from 'ng-recaptcha';
import { LoginDto } from './open-api';

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

  constructor(private recaptchaV3Service: ReCaptchaV3Service,
    private service: TestControllerService) {
  }

  get login() {    return this.loginForm.get('login')?.value || ''; }
  get password() {    return this.loginForm.get('password')?.value || ''; }

  public send(): void {
    this.recaptchaV3Service.execute('login')
    .subscribe((token: string) => {
      const body: LoginDto = {
        user: this.login,
        password: this.password
      };

      this.service.login(body, token).subscribe(res => {
        console.log("response: ", res);
      }, err => {
        console.error(err);
      })
    });
  }
}
