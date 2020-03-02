import React from 'react';
import { DropdownItemGroup, DropdownItem } from '@atlaskit/dropdown-menu'

export default () => (
    <div>
        <DropdownItemGroup title="Help">
            <DropdownItem>Grip Documentation</DropdownItem>
            <DropdownItem>Grip Community</DropdownItem>
            <DropdownItem>What's New</DropdownItem>
            <DropdownItem>Keyboard shortcuts</DropdownItem>
            <DropdownItem>About Grip</DropdownItem>
        </DropdownItemGroup>
        <DropdownItemGroup title="Legal">
            <DropdownItem>Terms of use</DropdownItem>
            <DropdownItem>Privacy policy</DropdownItem>
        </DropdownItemGroup>
    </div>
)
