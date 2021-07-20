# UMPConsentForm
UMP SDK
Obtaining Consent with the User Messaging Platform
Introduction
Under the Google EU User Consent Policy, you must make certain disclosures to your users in the European Economic Area (EEA) along with the UK and obtain their consent to use cookies or other local storage, where legally required, and to use personal data (such as AdID) to serve ads. This policy reflects the requirements of the EU ePrivacy Directive and the General Data Protection Regulation (GDPR).
To support publishers in meeting their duties under this policy, Google offers the User Messaging Platform (UMP) SDK, which replaces the previous open source Consent SDK. The UMP SDK has been updated to support the latest IAB standards. We've also simplified the process of setting up consent forms and listing ad providers. All of these configurations can now conveniently be handled in the Funding Choices UI.
It is a best practice to load a form every time the user launches your app, even if you determine consent is not required, so that the form is ready to display in case the user wishes to change their consent setting.
This guide walks you through how to install the SDK, implement the IAB solutions, and enable testing features.
