import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NewInviteViewComponent } from './components/new-invite-view/new-invite-view.component';
import { InviteFormViewComponent } from './components/invite-form-view/invite-form-view.component';
import { CheckEventViewComponent } from './components/check-event-view/check-event-view.component';
import { ResponseViewComponent } from './components/response-view/response-view.component';
import { ResponseSentViewComponent } from './components/response-sent-view/response-sent-view.component';

@NgModule({
  declarations: [
    AppComponent,
    NewInviteViewComponent,
    InviteFormViewComponent,
    CheckEventViewComponent,
    ResponseViewComponent,
    ResponseSentViewComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        HttpClientModule,
        MatInputModule,
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
