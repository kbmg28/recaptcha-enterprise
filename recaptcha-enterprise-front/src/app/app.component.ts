import { Component, OnInit } from '@angular/core';
import { ReCaptchaV3Service } from 'ng-recaptcha';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'recaptcha-enterprise-front';

  constructor(private recaptchaV3Service: ReCaptchaV3Service) {

  }

  public send(): void {
    this.recaptchaV3Service.execute('login')
    .subscribe((token: string) => {
      console.log(`Token [${token}] generated`);
    });
  }
}
