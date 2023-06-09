import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { RECAPTCHA_V3_SITE_KEY, RecaptchaV3Module } from 'ng-recaptcha';

import { environment } from './../environments/environment';
import { ReactiveFormsModule } from '@angular/forms';
import { ApiModule, BASE_PATH } from './open-api';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RecaptchaV3Module,
    ReactiveFormsModule,
    HttpClientModule,
    ApiModule
  ],
  providers: [
    {
      provide: RECAPTCHA_V3_SITE_KEY,
      useValue: environment.recaptcha.siteKey,
    },
    {
      provide: BASE_PATH,
      useValue: environment.API_BASE_PATH
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
