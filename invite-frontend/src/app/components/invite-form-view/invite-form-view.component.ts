import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { InviteService } from '../../services/invite.service';
import { RootService } from '../../services/root.service';

@Component({
    selector: 'app-invite-form-view',
    templateUrl: './invite-form-view.component.html',
    styleUrls: ['./invite-form-view.component.css'],
})
export class InviteFormViewComponent implements OnInit {
    formGroup: FormGroup;
    stage: number;
    progress: number;
    controlStages: Array<Array<string>>;
    guestList: Array<{name: string, mail: string}> = [];
    noGuestError = false;
    hash: string;
    eventLink: string;
    @ViewChild('firstInput', {static: true}) firstInput: ElementRef;
    @ViewChild('secondInput', {static: true}) secondInput: ElementRef;
    @ViewChild('thirdInput', {static: true}) thirdInput: ElementRef;
    @ViewChild('submitButton', {static: true}) submitButton: ElementRef;

    stageTitle: string;
    stageTitles = ['Creating New Event', 'Basic Information', 'Guests List', ''];
    showMessageForm: boolean = false;

    constructor(private inviteService: InviteService,
                private root: RootService,
                private router: Router) {
    }

    ngOnInit() {
        if (!this.inviteService.newInviteHash)
            this.router.navigate(['']);
        let pattern = /^[\d]*\s[\w,.\s]+\s[\d]+[.]?/gi;
        this.formGroup = new FormGroup({
            'eventName': new FormControl('', [Validators.required, Validators.minLength(5), this.onlyLetters]),
            'replyAddress': new FormControl('', [Validators.required, Validators.email]),
            'date': new FormControl(new Date().toISOString().split('T')[0], [Validators.required]),
            'time': new FormControl('08:00', [Validators.required]),
            'location': new FormControl('', [Validators.required]),
            'guestName': new FormControl('', [Validators.required]),
            'guestMail': new FormControl('', [Validators.required, Validators.email]),
            'invitationContext': new FormControl(''),
            'hash': new FormControl(this.getHash(), [Validators.required]),
        });

        this.controlStages = [
            ['eventName', 'replyAddress'],
            ['date', 'time', 'location'],
            ['guestName', 'guestMail'],
        ];

        this.setStage(0);
        this.eventLink = this.root.BASE_URL + '/check/' + this.inviteService.newInviteHash;
        this.firstInput.nativeElement.focus();
    }

    submitForm() {
        const data = InviteFormViewComponent.createSendData(this.formGroup, this.guestList);
        this.inviteService.recordEvent(data).subscribe(
            () => this.router.navigate(['check', this.hash]),
            error => console.warn(error)
        );
    }

    setStage(n: number) {
        this.stage = n;
        this.stageTitle = this.stageTitles[n];
        this.progress = (this.stage + 1) / (this.controlStages.length + 1);
        setTimeout(() => this.switchFocus(), 100);
    }

    nextStage() {
        if (this.stageIsValid() && this.stage <= this.controlStages.length) {
            this.setStage(this.stage + 1);
        }
    }

    prevStage() {
        this.setStage(this.stage - 1);
    }

    addGuest(event?) {
        if (event)
            event.preventDefault();
        if (this.stageIsValid()) {
            this.guestList.push({
                name: this.formGroup.controls.guestName.value,
                mail: this.formGroup.controls.guestMail.value,
            });
            this.formGroup.controls.guestName.setValue('');
            this.formGroup.controls.guestMail.setValue('');
            this.formGroup.controls.guestName.markAsPending();
            this.formGroup.controls.guestMail.markAsPending();
            this.formGroup.controls.guestName.markAsUntouched();
            this.formGroup.controls.guestMail.markAsUntouched();
            this.noGuestError = false;
            this.thirdInput.nativeElement.focus();
        }
    }

    gotGuests() {
        if (this.guestList.length > 0) {
            this.setStage(this.stage + 1);
        } else {
            this.noGuestError = true;
        }
    }

    deleteGuest(i: number) {
        this.guestList.splice(i, 1);
    }

    getHash() {
        this.hash = this.inviteService.newInviteHash;
        return this.hash;
    }


    copyLinkToClipboard() {
        document.addEventListener('copy', (e: ClipboardEvent) => {
            e.clipboardData.setData('text/plain', this.eventLink);
            e.preventDefault();
            document.removeEventListener('copy', null);
        });
        document.execCommand('copy');
        document.getElementById('copyLink').innerText = '[link copied]';
        this.selectText('eventLink');
    }

    selectText(containerId) {
        if (window.getSelection) {
            const range = document.createRange();
            range.selectNode(document.getElementById(containerId));
            window.getSelection().removeAllRanges();
            window.getSelection().addRange(range);
        }
    }

    stageIsValid() {
        for (let control of this.controlStages[this.stage]) {
            if (this.formGroup.get(control).invalid) {
                this.formGroup.controls[control].markAsTouched();
                return false;
            }
        }
        return true;
    }

    private switchFocus() {
        switch (this.stage) {
            case 0:
                this.firstInput.nativeElement.focus();
                break;
            case 1:
                this.secondInput.nativeElement.focus();
                break;
            case 2:
                this.thirdInput.nativeElement.focus();
                break;
            case 3:
                this.submitButton.nativeElement.focus();
                break;
            default:
        }
    }

    onlyLetters(formControl: FormControl): { required: boolean } {
        let value: string = formControl.value;
        return  new RegExp('/[^a-zA-Z0-9\s,.\-]/gi').test(value) ? {required: true} : null;
    }

    private static createSendData(formGroup: FormGroup, guests) {
        const formValue = formGroup.value;
        delete formValue.guestName;
        delete formValue.guestMail;
        formValue.dateTime = formValue.date + ' ' + formValue.time;
        formValue.guestList = guests;
        return formValue;
    }

    addMessageForm() {
        this.showMessageForm = !this.showMessageForm;
    }
}
