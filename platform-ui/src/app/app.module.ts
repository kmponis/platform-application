import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { SignInComponent } from './user/sign-in/sign-in.component';
import { HomeComponent } from './home/home.component';
import { UserService } from './_services/user.service';
import { HttpClientModule } from '@angular/common/http';
import { AuthGuard } from './_auth/auth.guard';
import { Idle } from '@ng-idle/core';
import { NgIdleKeepaliveModule } from '@ng-idle/keepalive';
import { TimeoutService } from './_services/timeout.service';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { LogService } from './_services/log.service';
import { AdminGuard } from './_auth/admin.guard';
import { MatButtonModule, MatFormFieldModule, MatInputModule } from '@angular/material';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StudentComponent } from './students/student.component';
import { UserInputPopUpComponent } from './students/user-input-pop-up/user-input-pop-up.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    SignInComponent,
    NavigationBarComponent,
    HomeComponent,
    StudentComponent,
    UserInputPopUpComponent
  ],
  entryComponents: [
    UserInputPopUpComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    NgIdleKeepaliveModule.forRoot(),
    MatDialogModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    BrowserAnimationsModule
  ],
  providers: [
    AuthGuard,
    AdminGuard,
    UserService,
    TimeoutService,
    LogService,
    Idle
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
