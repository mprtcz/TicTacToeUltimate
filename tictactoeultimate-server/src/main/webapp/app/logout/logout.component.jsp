<section mdl-upgrade class="section--center tab-content">
    <div class="mdl-card loginCard mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
        <div *ngIf="getCurrentUser() == null" >
            <h4 class="mdl-card__title">You have been logged out successfully!</h4><br/>
            <i class="material-icons md-36">announcement</i>
        </div>
    </div>
</section>