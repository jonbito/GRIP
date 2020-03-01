import React from 'react';
import { DropdownItemGroup, DropdownItem } from '@atlaskit/dropdown-menu'

export default () => (
    <div>
        <DropdownItemGroup title="Heading">
            <DropdownItem>Hello it with some really quite long text here.</DropdownItem>
            <DropdownItem>Some text 2</DropdownItem>
            <DropdownItem isDisabled>Some disabled text</DropdownItem>
            <DropdownItem>Some more text</DropdownItem>
            <DropdownItem href="//atlassian.com" target="_new">
                A link item
            </DropdownItem>
        </DropdownItemGroup>
    </div>
)
