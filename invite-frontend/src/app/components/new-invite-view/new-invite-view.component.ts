import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InviteService } from '../../services/invite.service';

@Component({
    selector: 'app-new-invite-view',
    templateUrl: './new-invite-view.component.html',
    styleUrls: ['./new-invite-view.component.css'],
})
export class NewInviteViewComponent implements OnInit {

    constructor(private router: Router,
                private inviteService: InviteService) { }

    ngOnInit() {
    }

    toInviteForm() {
        this.inviteService.generateNewEventHash();
        this.router.navigate(['/create-new']);
    }
}
