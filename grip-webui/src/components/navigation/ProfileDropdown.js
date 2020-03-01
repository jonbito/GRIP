import React, {Component} from 'react';
import {DropdownItem, DropdownItemGroup} from "@atlaskit/dropdown-menu";

export default class extends Component {
    render() {
        return (
            <div>
                <DropdownItemGroup>
                    <DropdownItem>Give us feedback</DropdownItem>
                </DropdownItemGroup>
                <DropdownItemGroup title="Grip">
                    <DropdownItem>Settings</DropdownItem>
                </DropdownItemGroup>
                <DropdownItemGroup title="Jonathan Bishop">
                    <DropdownItem href="/profile">Profile</DropdownItem>
                    <DropdownItem href="/people/1">Account settings</DropdownItem>
                    <DropdownItem href="/logout">Log out</DropdownItem>
                </DropdownItemGroup>
            </div>
        );
    }
}
