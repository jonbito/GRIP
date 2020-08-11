import React from 'react';
import {PageHeader, Button, Table, Input, Space} from "antd";
import {EllipsisOutlined, SearchOutlined} from "@ant-design/icons";
import {Link} from "react-router-dom";

export default ({ history }) => {

    const columns = [
        {
            title: 'Name',
            dataIndex: 'name',
            render: (_, record) => (
                <Link to="/">{record.name}</Link>
            ),
            sorter: (a, b) => a.name.localeCompare(b.name),
            showSorterTooltip: false
        },
        {
            title: '',
            render: (_, record) => {
                return (
                    <Button icon={<EllipsisOutlined />} size="small" />
                )
            },
            align: 'right',
            width: 50
        }
    ];
    
    const data = [
        {
            key: '1',
            name: 'asg asdga efasdgdsg',
            age: 32,
            address: 'New York No. 1 Lake Park',
        },
        {
            key: '2',
            name: 'a',
            age: 42,
            address: 'London No. 1 Lake Park',
        },
        {
            key: '3',
            name: 'z',
            age: 32,
            address: 'Sidney No. 1 Lake Park',
        },
        {
            key: '4',
            name: 'b',
            age: 32,
            address: 'London No. 2 Lake Park',
        },
    ];
    
    return (
        <div>
            <PageHeader
                title="Projects"
                extra={[
                    <Button type="primary" key="1">Create Project</Button>
                ]}
            >
                <div style={{width: 200, marginBottom: 20}}>
                    <Input suffix={<SearchOutlined />} />
                </div>
                <Table columns={columns} dataSource={data} />
            </PageHeader>
        </div>
    )
}