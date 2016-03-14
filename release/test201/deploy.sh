#!/bin/sh

############################################################
# deploy config
############################################################
REPO_PATH="/home/dms/git-repos/elanding-apps"

REPO_REMOTE_URL="https://gitcafe.com/digimobistudio/elanding-apps.git"
REPO_REMOTE_ALIAS_NAME="origin"

REPO_REMOTE_BRANCH="develop"
REPO_LOCAL_BRANCH="develop"

DEPLOY_PROJECT_NAME="e-lenovo-ec3-bigdata"
DEPLOY_TARGET="test201"

DEPLOY_TARGET_PATH="/home/dms/webapps_elanding/elanding-ec3-bigdata"
DEPLOY_TARGET_TOMCAT_HOME="/usr/local/tomcat_lenovo_ec3"
DEPLOY_TARGET_TOMCAT_PORT="12345"
#-----------------------------------------------------------

############################################################
# prepare
############################################################
project_path="$REPO_PATH/$DEPLOY_PROJECT_NAME"
war_bak_path="$DEPLOY_TARGET_PATH/war_bak"
deploy_time=`date +%F_%T`

# exe cmd
exe_task_cmd(){
    echo $1
    $1
}

if [ ! -d $DEPLOY_TARGET_PATH ]; then
    mkdir -p $DEPLOY_TARGET_PATH
fi

if [ ! -d $war_bak_path ]; then
    mkdir -p $war_bak_path
fi
#-----------------------------------------------------------

############################################################
# doing
############################################################
echo start deploy ...
echo .
echo REPO_PATH: $REPO_PATH
echo REPO_REMOTE_URL: $REPO_REMOTE_URL
echo DEPLOY_PROJECT_NAME: $DEPLOY_PROJECT_NAME
echo DEPLOY_TARGET: $DEPLOY_TARGET
echo DEPLOY_TARGET_PATH: $DEPLOY_TARGET_PATH
echo DEPLOY_TARGET_TOMCAT_HOME: $DEPLOY_TARGET_TOMCAT_HOME
echo .
echo project_path: $project_path
echo war_bak_path: $war_bak_path
echo deploy_time: $deploy_time
echo .

echo 1. checkout latest code from $REPO_REMOTE_URL
exe_task_cmd "cd $REPO_PATH"
exe_task_cmd "git pull $REPO_REMOTE_ALIAS_NAME $REPO_REMOTE_BRANCH $REPO_LOCAL_BRANCH"
echo ok!
echo .

echo 2. mvn install all modules
exe_task_cmd "cd $project_path"
exe_task_cmd "mvn install"
echo ok!
echo .

echo 3. copy build target to install dir
exe_task_cmd "cp $project_path/target/$DEPLOY_PROJECT_NAME.war $DEPLOY_TARGET_PATH/$DEPLOY_PROJECT_NAME.$deploy_time.war"
echo ok!
echo .

echo 4. shutdown server
exe_task_cmd "$DEPLOY_TARGET_TOMCAT_HOME/bin/shutdown.sh"
echo "force kill"
netstat -tlnp|grep $DEPLOY_TARGET_TOMCAT_PORT|awk '{print $7}'|awk -F '/' '{print "kill -9", $1}' | sh
echo ok!
echo .

echo 5. backup old war
exe_task_cmd "cd $DEPLOY_TARGET_PATH"
exe_task_cmd "zip -r $war_bak_path/$DEPLOY_PROJECT_NAME.bak_$deploy_time.war $DEPLOY_TARGET_PATH/$DEPLOY_PROJECT_NAME"
echo ok!
echo .

echo 6. unzip target war
exe_task_cmd "rm $DEPLOY_TARGET_PATH/$DEPLOY_PROJECT_NAME -rf"
exe_task_cmd "unzip $DEPLOY_TARGET_PATH/$DEPLOY_PROJECT_NAME.$deploy_time.war -d $DEPLOY_TARGET_PATH/$DEPLOY_PROJECT_NAME"
echo ok!
echo .

echo 7. copy release config file
exe_task_cmd "cp $project_path/release/$DEPLOY_TARGET/*.properties $DEPLOY_TARGET_PATH/$DEPLOY_PROJECT_NAME/WEB-INF/classes/"
echo ok!
echo .

echo 8. start server
exe_task_cmd "$DEPLOY_TARGET_TOMCAT_HOME/bin/startup.sh"
echo ok!
echo .

echo .

echo deploy ok!
echo .

echo show java process
exe_task_cmd "jps -v"
echo ok!
echo .

echo show netstat
exe_task_cmd "netstat -tulpn"
echo ok!
echo .


