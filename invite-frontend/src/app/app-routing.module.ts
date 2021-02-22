import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CheckEventViewComponent } from './components/check-event-view/check-event-view.component';
import { InviteFormViewComponent } from './components/invite-form-view/invite-form-view.component';
import { NewInviteViewComponent } from './components/new-invite-view/new-invite-view.component';
import { ResponseSentViewComponent } from './components/response-sent-view/response-sent-view.component';
import { ResponseViewComponent } from './components/response-view/response-view.component';


const routes: Routes = [
    {path: '', component: NewInviteViewComponent},
    {path: 'create-new', component: InviteFormViewComponent},
    {path: 'sent', component: ResponseSentViewComponent},
    {path: 'check/:hash', component: CheckEventViewComponent},
    {path: ':hash/:guest', component: ResponseViewComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {
}
