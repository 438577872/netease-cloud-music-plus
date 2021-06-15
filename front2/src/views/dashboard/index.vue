<template>
  <div class="dashboard-container">
    <div>
      <el-input v-model="queryKey" style="width: 600px"/>

      <el-button style="margin-left: 20px" @click="handleFind">
        查询
      </el-button>
      <el-button type="primary" @click="handleOpen">
        上传歌曲
      </el-button>
      <UploadDialog :visible="dialog"/>
      <UpdateDialog :visible="dialog" :music="musicInfo"/>
    </div>
    <el-divider/>
    <el-table border :data="list">
      <el-table-column prop="id" label="id"></el-table-column>
      <el-table-column prop="musicName" label="歌曲名称"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column prop="musicTime" label="歌曲时长">
        <template slot-scope="{row}">
          {{parseInt(row.musicTime / 60)}}分 {{row.musicTime % 60}}秒
        </template>
      </el-table-column>
      <el-table-column prop="uploadTime" label="上传时间"></el-table-column>
      <el-table-column prop="imgSrc" label="封面"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="{row}">
          <el-button type="danger" @click="handleDelete(row)">
            删除
          </el-button>
          <el-button @click="handleUpdate(row)"></el-button>
        </template>
      </el-table-column>
    </el-table>


  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import UploadDialog from '../../components/Dialog/UploadDialog'
  import { deleteMusic, getMusic } from '../../api/music'
  import UpdateDialog from '../../components/Dialog/UpdateDialog'

  export default {
    name: 'Dashboard',
    components: { UpdateDialog, UploadDialog },
    computed: {
      // ...mapGetters([
      //   'name'
      // ])
    },
    data() {
      return {
        dialog: {
          upload: false,
          update: false
        },
        list: null,
        queryKey: '',
        musicInfo:{}
      }
    },
    methods: {
      handleOpen() {
        this.dialog.upload = true
      },
      handleFind() {
        getMusic({ key: this.queryKey }).then(resp => {
          this.list = resp.data
        })
      },
      handleDelete(row) {
        deleteMusic(row.id).then(resp => {
          this.$message.success('删除成功')
          this.handleFind()
        })
      },
      handleUpdate(row) {
        this.musicInfo = row
        this.dialog.update = true
      }
    },
    mounted() {
      this.handleFind()
    }
  }
</script>

<style lang="scss" scoped>
  .dashboard {
    &-container {
      margin: 30px;
    }

    &-text {
      font-size: 30px;
      line-height: 46px;
    }
  }
</style>
