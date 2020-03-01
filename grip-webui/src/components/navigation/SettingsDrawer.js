import React, {Component} from 'react';
import {MenuGroup, Section, HeadingItem, ButtonItem} from '@atlaskit/menu';
import PeopleGroupIcon from '@atlaskit/icon/glyph/people-group';
import CreditcardIcon from '@atlaskit/icon/glyph/creditcard';
import BillingIcon from '@atlaskit/icon/glyph/billing';
import FolderIcon from '@atlaskit/icon/glyph/folder';
import ScreenIcon from '@atlaskit/icon/glyph/screen';
import IssuesIcon from '@atlaskit/icon/glyph/issues';

export default class extends Component {
    render() {
        return (
            <MenuGroup>
                <Section>
                    <HeadingItem>Grip Admin</HeadingItem>
                    <ButtonItem
                        elemBefore={(<PeopleGroupIcon primaryColor="#abc123" />)}
                        description="Add users, groups, and manage access requests."
                    >
                        User management
                    </ButtonItem>
                    <ButtonItem
                        elemBefore={(<CreditcardIcon primaryColor="#abc123" />)}
                        description="Update your billing details and see pricing estimates."
                    >
                        Billing
                    </ButtonItem>
                    <ButtonItem
                        elemBefore={(<BillingIcon primaryColor="#abc123" />)}
                        description="Change your plan, add new products, or start a trial."
                    >
                        Manage subscriptions
                    </ButtonItem>
                </Section>
                <Section>
                    <HeadingItem>Grip Settings</HeadingItem>
                    <ButtonItem
                        elemBefore={(<ScreenIcon primaryColor="#abc123" />)}
                        description="Manage your general configuration, global permissions, look and feel and more."
                    >
                        System
                    </ButtonItem>
                    <ButtonItem
                        elemBefore={(<FolderIcon primaryColor="#abc123" />)}
                        description="Manage your project categories."
                    >
                        Projects
                    </ButtonItem>
                    <ButtonItem
                        elemBefore={(<IssuesIcon primaryColor="#abc123" />)}
                        description="Configure your issue types, workflows, screens, custom fields and more."
                    >
                        Issues
                    </ButtonItem>
                </Section>
            </MenuGroup>
        );
    }
}
